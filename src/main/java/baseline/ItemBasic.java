package baseline;

// Class is used only for JSON Parsing

class ItemBasic {
    private String name;
    private String serialNumber;
    private String value;

    public ItemBasic(){
        name = "";
        serialNumber = "";
        value = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return "baseline.Item [ name: "+name+", serialNumber: "+ serialNumber+ ", value: " + value + " ]";
    }
}