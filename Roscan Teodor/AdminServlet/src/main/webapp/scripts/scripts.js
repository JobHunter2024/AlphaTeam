function addPathInput(value = null) {
    const pathContainer = document.getElementById("pathContainer");

    const pathDiv = document.createElement("div");
    pathDiv.className = "path-entry";

    const valueInput = document.createElement("input");
    valueInput.type = "text";
    valueInput.name = "pathValue";
    valueInput.placeholder = "Enter Value";
    valueInput.required = true;
    valueInput.value = value ? value : "";

    const removeButton = document.createElement("button");
    removeButton.type = "button";
    removeButton.innerText = "Remove";
    removeButton.onclick = function () {
        pathContainer.removeChild(pathDiv);
    };

    pathDiv.appendChild(valueInput);
    pathDiv.appendChild(removeButton);

    pathContainer.appendChild(pathDiv);
}

function toggleMenu() {
    const nav = document.getElementById("nav");
    nav.classList.toggle("show-on-small");
    nav.classList.toggle("hide-on-large");
}

function updateMenu() {
    const nav = document.getElementById("nav");
    if (window.innerWidth > 860) {
        nav.classList.remove("show-on-small");
        nav.classList.remove("hide-on-large");
    } else if (nav.classList.contains("show-on-small")) {
        nav.classList.add("hide-on-large");
    }
}

window.addEventListener('resize', updateMenu);


function displayJson(jsonObj) {
    const jsonContainer = document.getElementById('json-content');
    jsonContainer.innerHTML = formatJson(jsonObj);
}

function formatJson(jsonObj) {
    let formattedJson = '';
    for (let key in jsonObj) {
        if (jsonObj.hasOwnProperty(key)) {
            const value = jsonObj[key];
            formattedJson += `<span class="key">"${key}"</span>: `;

            if (typeof value === 'string') {
                formattedJson += `<span class="string">"${value}"</span>`;
            } else if (typeof value === 'number') {
                formattedJson += `<span class="number">${value}</span>`;
            } else if (typeof value === 'boolean') {
                formattedJson += `<span class="boolean">${value}</span>`;
            } else if (value === null) {
                formattedJson += `<span class="null">${value}</span>`;
            }

            formattedJson += ', ';
        }
    }
    return formattedJson.slice(0, -2); // Remove the last comma
}