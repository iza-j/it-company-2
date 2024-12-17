package com.solvd.itcompany2.corporatestructure;

import com.solvd.itcompany2.helpers.Territory;

import java.util.function.Function;

import static com.solvd.itcompany2.helpers.Formatter.*;

public enum Office {

    POZNAN(Territory.POLAND, "ul. A. Szamarzewskiego 89/AB, 60–568 Poznań", "+48 61 829 2307"),
    SAN_FRANCISCO(Territory.CALIFORNIA, "505 Florida St, San Francisco, California 94110, US", "(918) 258-5765"),
    YOKOHAMA(Territory.JAPAN, "279-25 Yamashitacho, Naka-ku, Yokohama City, Kanagawa Prefecture, Japan 231-0023", "0120-15-8686");

    private final Territory territory;
    private final String address;
    private final String phoneNumber;

    private final Function<Office, String> formatName = office -> ansiColor(cyanFG) + office.name().replace("_", " ") + ansiColor(reset);

    Office(Territory territory, String address, String phoneNumber) {
        this.territory = territory;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getInfo() {
        return new StringBuilder()
                .append(formatName.apply(this))
                .append("\nTime right now: ")
                .append(this.territory.getTimeHourDate())
                .append("\nAddress: ")
                .append(this.address)
                .append("\nPhone number: ")
                .append(this.phoneNumber)
                .append("\n")
                .toString();
    }

    public Territory getTerritory() {
        return territory;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}