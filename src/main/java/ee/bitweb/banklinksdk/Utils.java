package ee.bitweb.banklinksdk;

/**
 * Created by tobre on 18/03/2017.
 */
public class Utils {

    /**
     * Swedbank 22 or 11
     * SEB 10
     * Sampo 33
     * Nordea 17
     * Krediidipank 42
     * Citadele Pank 12
     * LHV Pank 77
     * Bank DnB NORD 96
     * Tallinna Äripank 93
     */
    public static String getBankNameByAccountNumber(String accountNumber) {
        Integer numbers = Integer.parseInt(accountNumber.substring(0, 2));
        switch (numbers) {
            case 22:
            case 11:
                return "Swedbank";
            case 10:
                return "SEB";
            case 33:
                return "Danske";
            case 17:
                return "Nordea";
            case 42:
                return "Krediidipank";
            case 16:
                return "Eesti Pank";
            case 55:
                return "Versobank";
            case 12:
                return "Citadele Pank";
            case 77:
                return "LHV Pank";
            case 83:
                return "Svenska Handelsbanken";
            case 51:
                return "Pohjola Bank plc Eesti filiaal";
            case 96:
                return "Bank DnB NORD";
            case 93:
                return "Tallinna Äripank";
            case 75:
                return "BIGBANK AS";
            default:
                return "Unknown";
        }
    }

    public static String getBankNameByIban(String iban) {
        if (iban == "" || iban == null) {
            return "Unknown";
        }

        Integer numbers = Integer.parseInt(iban.substring(4, 6));

        switch (numbers) {
            case 22:
                return "Swedbank";
            case 16:
                return "Eesti Pank";
            case 10:
                return "SEB";
            case 33:
                return "Danske";
            case 17:
                return "Nordea";
            case 12:
                return "Citadele Pank";
            case 55:
                return "Versobank";
            case 42:
                return "Krediidipank";
            case 83:
                return "Svenska Handelsbanken";
            case 51:
                return "Pohjola Bank plc Eesti filiaal";
            case 77:
                return "LHV Pank";
            case 75:
                return "BIGBANK";
            case 96:
                return "DNB Pank";
            case 00:
                return "Tallinna Äripank";
            default:
                return "Unknown";
        }
    }

    /*public Integer function calculateRefNoChecksum(String refNo) {
        Integer[] weights = {7, 3, 1};
        Integer sl = refNo.length();
        Integer st = sl;
        Integer total = 0;

        while(sl > 0 && substr($refNo, --$sl, 1) >= "0"){
            $total += substr($refNo, ($st-1)-$sl, 1)* $weights[($sl%3)];
        }
        $checksum = ((ceil(($total/10))*10)-$total);
        return $checksum;
    }

    public static function validateReferenceNumber($refNo) {
        if(empty($refNo)) {
            return false;
        }
        if (!preg_match("/^[0-9]+$/", $refNo)) {
            return false;
        }
        if(strlen($refNo) > 20) {
            return false;
        }
        $inputChecksum = $refNo[strlen($refNo) - 1];
        $checksum = self::calculateRefNoChecksum(substr($refNo, 0, strlen($refNo) - 1));
        if($inputChecksum != $checksum) {
            return false;
        }
        return true;
    }

    public static function generateReferenceNumber($nr)
    {
        $nr = (string)$nr;
        $kaal = array(7, 3, 1);
        $sl = $st = strlen($nr);
        $total = 0;
        while ($sl > 0 and substr($nr, --$sl, 1) >= "0") {
        $total += substr($nr, ($st - 1) - $sl, 1) * $kaal[($sl % 3)];
    }
        $kontrollnr = ((ceil(($total / 10)) * 10) - $total);
        return $nr . $kontrollnr;
    }
    */
}
