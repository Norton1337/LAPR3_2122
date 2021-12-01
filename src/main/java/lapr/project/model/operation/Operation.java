package lapr.project.model.operation;

public class Operation {

        private String id;
        private int x;
        private int y;
        private int z;
        private String containerId;
        private String cargoManifestId;

        public Operation(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getZ() {
            return z;
        }

        public void setZ(int z) {
            this.z = z;
        }

        public String getContainerId() {
            return containerId;
        }

        public void setContainerId(String containerId) {
            this.containerId = containerId;
        }

        public String getCargoManifestId() {
            return cargoManifestId;
        }

        public void setCargoManifestId(String cargoManifestId) {
            this.cargoManifestId = cargoManifestId;
        }

}

