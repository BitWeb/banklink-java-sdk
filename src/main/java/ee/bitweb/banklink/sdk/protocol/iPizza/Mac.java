package ee.bitweb.banklink.sdk.protocol.iPizza;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by tobre on 20/03/2017.
 */
public class Mac {

    private static Certificate getPublicKey(String pem) throws IOException, GeneralSecurityException {
        PEMParser parser = new PEMParser(new StringReader(pem));

        X509CertificateHolder obj = (X509CertificateHolder) parser.readObject();
        byte[] encodedPublicKey = obj.getEncoded();

        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        return cf.generateCertificate(new ByteArrayInputStream(encodedPublicKey));
    }


    private static RSAPrivateKey getPrivateKey(String pem) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        PEMParser parser = new PEMParser(new StringReader(pem));
        Object privateKey = parser.readObject();

        PEMKeyPair pair = (PEMKeyPair) privateKey;

        if (privateKey == null) {
            throw new InvalidKeySpecException("Private key not in right format");
        }

        byte[] encodedPrivateKey = pair.getPrivateKeyInfo().getEncoded();

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
        return (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
    }


    static String sign(String mac, String privateKey) throws GeneralSecurityException, IOException {
        RSAPrivateKey privKey = getPrivateKey(privateKey);
        Signature s = Signature.getInstance("SHA1withRSA");
        s.initSign(privKey);
        s.update(ByteBuffer.wrap(mac.getBytes()));
        byte[] signature = s.sign();

        return new String(Base64.encodeBase64(signature));
    }

    static Boolean verify(String mac, String signature, String publicKey) throws GeneralSecurityException, IOException {
        Certificate key = getPublicKey(publicKey);

        Security.addProvider(new BouncyCastleProvider());

        Signature s = Signature.getInstance("SHA1withRSA", "BC");
        s.initVerify(key);

        s.update(ByteBuffer.wrap(mac.getBytes()));

        return s.verify(Base64.decodeBase64(signature.getBytes()));
    }

}
