public class Company extends DVKE<Company> implements Comparable<Company> {
    private long   index;
    private String organizationID;
    private String name;
    private String website;
    private String country;
    private String description;
    private int    founded;
    private String industry;
    private int    numberOfEmployees;

    public Company(long index,
                   String organizationID,
                   String name,
                   String website,
                   String country,
                   String description,
                   int founded,
                   String industry,
                   int numberOfEmployees)
    {
        super(null, null, null);
        this.index              = index;
        this.organizationID     = organizationID;
        this.name               = name;
        this.website            = website;
        this.country            = country;
        this.description        = description;
        this.founded            = founded;
        this.industry           = industry;
        this.numberOfEmployees  = numberOfEmployees;
        // im Node-Feld auf sich selbst verweisen
        this.setData(this);
    }

    // --- Getter/Setter ---
    public long   getIndex()             { return index; }
    public String getOrganizationID()    { return organizationID; }
    public String getName()              { return name; }
    public String getWebsite()           { return website; }
    public String getCountry()           { return country; }
    public String getDescription()       { return description; }
    public int    getFounded()           { return founded; }
    public String getIndustry()          { return industry; }
    public int    getNumberOfEmployees() { return numberOfEmployees; }

    public void setIndex(long index)                         { this.index = index; }
    public void setOrganizationID(String organizationID)     { this.organizationID = organizationID; }
    public void setName(String name)                         { this.name = name; }
    public void setWebsite(String website)                   { this.website = website; }
    public void setCountry(String country)                   { this.country = country; }
    public void setDescription(String description)           { this.description = description; }
    public void setFounded(int founded)                      { this.founded = founded; }
    public void setIndustry(String industry)                 { this.industry = industry; }
    public void setNumberOfEmployees(int numberOfEmployees)  { this.numberOfEmployees = numberOfEmployees; }

    @Override
    public int compareTo(Company other) {
        switch (Config.SORT_KEY) {
            case "index":
                return Long.compare(this.index, other.index);
            case "organizationID":
                return this.organizationID.compareTo(other.organizationID);
            case "country":
                return this.country.compareTo(other.country);
            case "founded":
                return Integer.compare(this.founded, other.founded);
            case "numberOfEmployees":
                return Integer.compare(this.numberOfEmployees, other.numberOfEmployees);
            case "name":
            default:
                return this.name.compareTo(other.name);
        }
    }

    @Override
    public String toString() {
        // CSV-Zeile
        return index + Config.DELIMITER +
                organizationID + Config.DELIMITER +
                name + Config.DELIMITER +
                website + Config.DELIMITER +
                country + Config.DELIMITER +
                description + Config.DELIMITER +
                founded + Config.DELIMITER +
                industry + Config.DELIMITER +
                numberOfEmployees;
    }

    public static String getCsvHeader() {
        return "index" + Config.DELIMITER +
                "organizationID" + Config.DELIMITER +
                "name" + Config.DELIMITER +
                "website" + Config.DELIMITER +
                "country" + Config.DELIMITER +
                "description" + Config.DELIMITER +
                "founded" + Config.DELIMITER +
                "industry" + Config.DELIMITER +
                "numberOfEmployees";
    }
}
