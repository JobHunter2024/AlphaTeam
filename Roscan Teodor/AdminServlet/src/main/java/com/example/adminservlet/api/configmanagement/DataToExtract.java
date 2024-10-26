package com.example.adminservlet.api.configmanagement;

import java.net.URL;
import java.util.Dictionary;
import java.util.Objects;
import java.util.UUID;

public class DataToExtract {
    public URL target;
    public Dictionary<String, String> path;
    public UUID uuid;

    public DataToExtract(URL target, Dictionary<String, String> path, UUID uuid) {
        this.target = target;
        this.path = path;
        this.uuid = uuid;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DataToExtract)) {
            return false;
        }

        DataToExtract other = (DataToExtract) obj;

        return Objects.equals(this.target, other.target) &&
                Objects.equals(this.path, other.path) &&
                Objects.equals(this.uuid, other.uuid);
    }

    public URL getTarget() {
        return target;
    }

    public Dictionary<String, String> getPath() {
        return path;
    }

    public UUID getUUID() {
        return uuid;
    }
}
