package ee.bitweb.banklink.sdk.protocol.iPizza;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.*;
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

    protected static final Log logger = LogFactory.getLog(Mac.class);


    public static PublicKey getPublicKeyFromCertificateString(String pem) throws IOException, GeneralSecurityException {

        logger.info("Creating public key from certificate string");
        PEMParser parser = new PEMParser(new StringReader(pem));

        X509CertificateHolder obj = (X509CertificateHolder) parser.readObject();
        byte[] encodedPublicKey = obj.getEncoded();

        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        return cf.generateCertificate(new ByteArrayInputStream(encodedPublicKey)).getPublicKey();
    }

    public static RSAPublicKey getPublicKeyFromKeyString(String publicKeyContent) throws NoSuchAlgorithmException, InvalidKeySpecException {
        logger.info("Creating public key from key string");
        publicKeyContent = publicKeyContent.replaceAll("\\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");

        KeyFactory kf = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyContent));
        return (RSAPublicKey) kf.generatePublic(keySpecX509);
    }

    public static RSAPrivateKey getPrivateKey(String pem) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        logger.info("Creating private key from string");
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

    static String sign(String mac, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        logger.info("Signing MAC value: " + mac);
        Signature s = Signature.getInstance("SHA1withRSA");
        s.initSign(privateKey);
        s.update(ByteBuffer.wrap(mac.getBytes()));
        byte[] signatureArray = s.sign();
        String signature = new String(Base64.encodeBase64(signatureArray));

        logger.debug("Generated MAC signature: " + signature);

        return signature;
    }

    static Boolean verify(String mac, String signature, PublicKey publicKey) throws GeneralSecurityException, IOException {
        logger.info("Verifying signature :" + signature + " \n for MAC " + mac);
        Security.addProvider(new BouncyCastleProvider());
        Signature s = Signature.getInstance("SHA1withRSA", BouncyCastleProvider.PROVIDER_NAME);
        s.initVerify(publicKey);
        s.update(ByteBuffer.wrap(mac.getBytes(Charset.forName("UTF-8"))));

        Boolean verified = s.verify(Base64.decodeBase64(signature.getBytes()));

        logger.debug(verified ? "Signature is verified" : "Signature is not verified");

        return verified;
    }

}
