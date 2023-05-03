package com.example.base.client;

import com.example.base.core.config.AppSettings;
import com.example.base.model.TokenRequest;
import com.example.base.model.TokenRequestWithAppKey;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;

@Data
@Component
public class CommonClient {
    public static String ORDERID;
    public static String JWT;
    private static final String AES_KEY = "5TGB&YHN7UJM(IK<5TGB&YHN7UJM(IK<";
    private static final String AES_INIT_VECTOR = "!QAZ2WSX#EDC4RFV";




    //AppKey ile Jwt olustur
    public String getJwtWithAppKey() {
        TokenRequestWithAppKey tokenRequestWithAppKey = TokenRequestWithAppKey.builder()
                .appKey("AF7F2A37-CC4B-4F1C-87FD-FF3642F67ECB")
                .username("eazakli_newcheckout@gmail.com")
                .password("Sardes.123")
                .deviceFingerPrint("")
                .build();

        return JWT = given()
                .baseUri(AppSettings.Instance.loginUrlWithAppKey)
                .contentType(ContentType.JSON)
                .when()
                .body(tokenRequestWithAppKey)
                .post("api/Service/Login")
                .then()
                .extract()
                .path("JwtToken");
    }

    //Jwt olustur
    public void getJwt(String UserName, String Password) {
        TokenRequest tokenRequest = TokenRequest.builder()
                .em(UserName)
                .p(Password)
                .build();

        Response response = given()
                .log().body()
                .baseUri(AppSettings.Instance.loginUrl)
                .contentType(ContentType.JSON)
                .when()
                .body(tokenRequest)
                .post("uyelik/Customer/Login")
                .then()
                .log().body()
                .extract().response();

        JWT = response.getCookie("jwt");

    }



    public String generateSignature(String value, String secretKey) {
        String result;
        try {
            Mac hmacSHA512 = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA512");
            hmacSHA512.init(secretKeySpec);

            byte[] digest = hmacSHA512.doFinal(value.getBytes());
            BigInteger hash = new BigInteger(1, digest);
            result = hash.toString(16);
            if ((result.length() % 2) != 0) {
                result = "0" + result;
            }
        } catch (IllegalStateException | InvalidKeyException | NoSuchAlgorithmException ex) {
            throw new RuntimeException("Error: Failed to create signature", ex);
        }
        return result;
    }

    public String getRandumUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public String getRandomEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public String getFirstName() {
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    public String getLastName() {
        Faker faker = new Faker();
        return faker.name().lastName();
    }

    public String getGsmNumber(String gsmFormat) {
        gsmFormat = gsmFormat == null ? "90##########" : gsmFormat;
        Faker faker = new Faker();
        return faker.numerify(gsmFormat);
    }

    public String getFakeIdentityNumber() {
        Faker faker = new Faker();
        return faker.number().digits(11);
    }

    public String getFakeCardNumber() {
        Faker faker = new Faker();
        return faker.number().digits(16);
    }

    public String getFakeEncryptedIdentityNumber() {
        Faker faker = new Faker();
        return encryptAes256(faker.number().digits(11));
    }

    public String getFakeCode() {
        Faker faker = new Faker();
        return faker.crypto().sha1();
    }



    public Timestamp calculateDate(Integer dateCount) {
        if (dateCount != null) {
            Timestamp timestampDate = new Timestamp(new Date().getTime());
            Calendar calendarCreatedDate = Calendar.getInstance();

            calendarCreatedDate.setTimeInMillis(timestampDate.getTime());

            calendarCreatedDate.add(Calendar.DAY_OF_MONTH, dateCount);

            timestampDate = new Timestamp(calendarCreatedDate.getTime().getTime());
            return timestampDate;
        }
        return null;
    }

    public int randomValueByRange(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public double randomValueByRange(double min, double max) {
        double randomValue = ThreadLocalRandom.current().nextDouble(min, max);
        return round(randomValue, 2);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public String maskCardNumber(String cardNumber) {
        final int PREFIX_LENGTH = 6;
        final int SUFFIX_LENGTH = 4;
        //int maskedLength = cardNumber.length() - (PREFIX_LENGTH + SUFFIX_LENGTH);
        //String stars = "*".repeat(Math.max(0, maskedLength));
        return cardNumber.substring(0, PREFIX_LENGTH) + "*****" + cardNumber.substring(cardNumber.length() - SUFFIX_LENGTH);
    }

    public String checkTenant(String tenant) {
        if (tenant != null) {
            if (tenant.equals("") || tenant.equals("null"))
                return null;
        }
        return tenant;
    }

    public Timestamp convertDateTimeStamp(String date, String pattern) {

        if (pattern == null) pattern = "yyyy-MM-dd'T'HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(date));
        return Timestamp.valueOf(localDateTime);
    }

    public String convertToMd5(String data) {
        return DigestUtils
                .md5Hex(data).toLowerCase();
    }

    public String encryptAes256(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(AES_INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec sKeySpec = new SecretKeySpec(AES_KEY.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_16LE));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String decryptAes256(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(AES_INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec sKeySpec = new SecretKeySpec(AES_KEY.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original, StandardCharsets.UTF_16LE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String maskIdentityNumber(String identityNumber) {
        var firstThree = identityNumber.substring(0, 3);
        var lastThree = identityNumber.substring(identityNumber.length() - 3);

        return firstThree + "*****" + lastThree;
    }

}