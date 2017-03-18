package ee.bitweb.banklinksdk.protocol.iPizza;

/**
 * Created by tobre on 18/03/2017.
 */
public enum Fields {

    /**
     * Teenuse number
     */
    SERVICE ("VK_SERVICE", 4),

    /**
     * Kasutatav krüptologaritm
     */
    VERSION ("VK_VERSION", 3),

    /**
     * Päringu ID
     */
    STAMP("VK_STAMP", 20),

    /**
     * Maksmisele kuuluv summa
     */
    AMOUNT("VK_AMOUNT", 12),

    /**
     * Valuuta nimi
     */
    CURR("VK_CURR", 3),

    /**
     * Saaja konto number
     */
    ACC("VK_ACC", 34),

    /**
     * Maksekorralduse viitenumber
     */
    REF("VK_REF", 35),

    /**
     * Soovitav suhtluskeel
     */
    LANG("VK_LANG", 3),

    /**
     * Maksekorralduse number
     */
    T_NO("VK_T_NO", 20),

    /**
     * Saaja konto number
     */
    REC_ACC("VK_REC_ACC", 34),

    /**
     * Maksja konto number
     */
    SND_ACC("VK_SND_ACC", 34),

    /**
     * Maksekorralduse kuupäev
     */
    T_DATE("VK_T_DATE", 10),

    /**
     * Näitab seda, kas pakett oli saadetud automaatselt (`Y`) või mitte (`N`)
     */
    AUTO("VK_AUTO", 1),

    /**
     * Kokkuleppeline kasutaja indifikaator
     */
    USER("VK_USER", 16),

    /**
     * Paketi genereerimise kuupäev
     */
    DATE("VK_DATE", 10),

    /**
     * Paketi genereerimise kellaaeg
     */
    TIME("VK_TIME", 8),

    /**
     * Kokkuleppel standardiseeritav kasutaja isikuandmeid sisaldav väli
     */
    INFO("VK_INFO", 300),

    /**
     * Oodatava vastuspaketi kood
     */
    REPLY("VK_REPLY", 4),

    /**
     * Sõnumi koostaja (partneri) ID
     */
    SND_ID("VK_SND_ID"),

    /**
     * Saaja nimi
     */
    NAME("VK_NAME"),

    /**
     * Maksekorralduse seletus
     */
    MSG("VK_MSG"),

    /**
     * Kontrollkood e. allkiri
     */
    MAC("VK_MAC", 700),

    /**
     * URL, kuhu vastatakse edukal tehingu sooritamisel
     */
    RETURN_URL("VK_RETURN"),

    /**
     * URL, kuhu vastatakse ebaõnnestunud tehingu puhul
     */
    CANCEL_URL("VK_CANCEL", 255),

    /**
     * Saaja nimi
     */
    REC_NAME("VK_REC_NAME"),

    /**
     * Maksja nimi
     */
    SND_NAME("VK_SND_NAME"),

    /**
     * Päringu vastuvõtja ID (Kaupluse ID)
     */
    REC_ID("VK_REC_ID"),

    /**
     *
     * Päringu saatja poolt genereeritud juhuslik nonss (kasutatakse värskuse tagamiseks)
     */
    NONCE("VK_NONCE", 50),

    DATETIME("VK_DATETIME", 24),

    T_DATETIME("VK_T_DATETIME", 24),

    RID("VK_RID", 30),

    USER_NAME("VK_USER_NAME", 140),

    USER_ID("VK_USER_ID", 20),

    /*
     * Isikukoodi riik (kahetäheline kood vastavalt ISO 3166-1 standardile)
     */
    COUNTRY("VK_COUNTRY", 2),

    OTHER("VK_OTHER", 150),

    /*
     * Autentimisvahendi identifikaatori kood:
     * 1- ID-kaart;
     * 2- Mobiil-ID;
     * 5- ühekordsed koodid v.a. PIN-kalkulaator (Swedbank hetkel ühekordseid paroole ei kasuta);
     * 6- PIN-kalkulaator;
     * 7- korduvkasutusega kaart
     */
    TOKEN("VK_TOKEN", 2),

    /*
     * Sõnumi kodeering. UTF-8 (vaikeväärtus), ISO-8859-1 või WINDOWS-1257
     */
    ENCODING("VK_ENCODING", 12);

    public String name;
    public int length;

    Fields(String name, int length) {
        this.name = name;
        this.length = length;
    }

    Fields(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
