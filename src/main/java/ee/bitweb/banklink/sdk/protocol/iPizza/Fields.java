package ee.bitweb.banklink.sdk.protocol.iPizza;

import ee.bitweb.banklink.sdk.protocol.FieldDefinition;

/**
 * Created by tobre on 18/03/2017.
 */
public class Fields {

    /**
     * Teenuse number
     */
    public static FieldDefinition SERVICE = new FieldDefinition("VK_SERVICE", 4);

    /**
     * Kasutatav krüptologaritm
     */
    public static FieldDefinition VERSION = new FieldDefinition("VK_VERSION", 3);

    /**
     * Päringu ID
     */
    public static FieldDefinition STAMP = new FieldDefinition("VK_STAMP", 20);

    /**
     * Maksmisele kuuluv summa
     */
    public static FieldDefinition AMOUNT = new FieldDefinition("VK_AMOUNT", 12);

    /**
     * Valuuta nimi
     */
    public static FieldDefinition CURR = new FieldDefinition("VK_CURR", 3);
    /**
     * Saaja konto number
     */
    public static FieldDefinition ACC = new FieldDefinition("VK_ACC", 34);

    /**
     * Maksekorralduse viitenumber
     */
    public static FieldDefinition REF = new FieldDefinition("VK_REF", 35);

    /**
     * Soovitav suhtluskeel
     */
    public static FieldDefinition LANG = new FieldDefinition("VK_LANG", 3);

    /**
     * Maksekorralduse number
     */
    public static FieldDefinition T_NO = new FieldDefinition("VK_T_NO", 20);

    /**
     * Saaja konto number
     */
    public static FieldDefinition REC_ACC = new FieldDefinition("VK_REC_ACC", 3);

    /**
     * Maksja konto number
     */
    public static FieldDefinition SND_ACC = new FieldDefinition("VK_SND_ACC", 34);

    /**
     * Maksekorralduse kuupäev
     */
    public static FieldDefinition T_DATE = new FieldDefinition("VK_T_DATE", 10);

    /**
     * Näitab seda, kas pakett oli saadetud automaatselt (`Y`) või mitte (`N`)
     */
    public static FieldDefinition AUTO = new FieldDefinition("VK_AUTO", 1);

    /**
     * Kokkuleppeline kasutaja indifikaator
     */
    public static FieldDefinition USER = new FieldDefinition("VK_USER", 16);

    /**
     * Paketi genereerimise kuupäev
     */
    public static FieldDefinition DATE = new FieldDefinition("VK_DATE", 10);

    /**
     * Paketi genereerimise kellaaeg
     */
    public static FieldDefinition TIME = new FieldDefinition("VK_TIME", 8);

    /**
     * Kokkuleppel standardiseeritav kasutaja isikuandmeid sisaldav väli
     */
    public static FieldDefinition INFO = new FieldDefinition("VK_INFO", 300);

    /**
     * Oodatava vastuspaketi kood
     */
    public static FieldDefinition REPLY = new FieldDefinition("VK_REPLY", 4);

    /**
     * Sõnumi koostaja (partneri) ID
     */
    public static FieldDefinition SND_ID = new FieldDefinition("VK_SND_ID", 15);

    /**
     * Saaja nimi
     */
    public static FieldDefinition NAME = new FieldDefinition("VK_NAME", 70);

    /**
     * Maksekorralduse seletus
     */
    public static FieldDefinition MSG = new FieldDefinition("VK_MSG", 95);

    /**
     * Kontrollkood e. allkiri
     */
    public static FieldDefinition MAC = new FieldDefinition("VK_MAC", 700);

    /**
     * URL, kuhu vastatakse edukal tehingu sooritamisel
     */
    public static FieldDefinition RETURN_URL = new FieldDefinition("VK_RETURN", 255);

    /**
     * URL, kuhu vastatakse ebaõnnestunud tehingu puhul
     */
    public static FieldDefinition CANCEL_URL = new FieldDefinition("VK_CANCEL", 255);

    /**
     * Saaja nimi
     */
    public static FieldDefinition REC_NAME = new FieldDefinition("VK_REC_NAME", 70);

    /**
     * Maksja nimi
     */
    public static FieldDefinition SND_NAME = new FieldDefinition("VK_SND_NAME", 70);

    /**
     * Päringu vastuvõtja ID (Kaupluse ID)
     */
    public static FieldDefinition REC_ID = new FieldDefinition("VK_REC_ID", 15);

    /**
     *
     * Päringu saatja poolt genereeritud juhuslik nonss (kasutatakse värskuse tagamiseks)
     */
    public static FieldDefinition NONCE = new FieldDefinition("VK_NONCE", 50);

    public static FieldDefinition DATETIME = new FieldDefinition("VK_DATETIME", 24);


    public static FieldDefinition T_DATETIME = new FieldDefinition("VK_T_DATETIME", 24);

    public static FieldDefinition RID = new FieldDefinition("VK_RID", 30);

    public static FieldDefinition USER_NAME = new FieldDefinition("VK_USER_NAME", 140);

    public static FieldDefinition USER_ID = new FieldDefinition("VK_USER_ID", 20);

        /*
     * Isikukoodi riik (kahetäheline kood vastavalt ISO 3166-1 standardile)
     */

    public static FieldDefinition COUNTRY = new FieldDefinition("VK_COUNTRY", 2);
    public static FieldDefinition OTHER = new FieldDefinition("VK_OTHER", 150);

    /*
     * Autentimisvahendi identifikaatori kood:
     * 1- ID-kaart;
     * 2- Mobiil-ID;
     * 5- ühekordsed koodid v.a. PIN-kalkulaator (Swedbank hetkel ühekordseid paroole ei kasuta);
     * 6- PIN-kalkulaator;
     * 7- korduvkasutusega kaart
     */
    public static FieldDefinition TOKEN = new FieldDefinition("VK_TOKEN", 2);

    /*
     * Sõnumi kodeering. UTF-8 (vaikeväärtus), ISO-8859-1 või WINDOWS-1257
     */
    public static FieldDefinition ENCODING = new FieldDefinition("VK_ENCODING", 12);
}
