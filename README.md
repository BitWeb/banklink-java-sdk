[![Build Status](https://travis-ci.org/BitWeb/banklink-java-sdk.svg?branch=master)](https://travis-ci.org/BitWeb/banklink-java-sdk)
[![codecov](https://codecov.io/gh/BitWeb/banklink-java-sdk/branch/master/graph/badge.svg)](https://codecov.io/gh/BitWeb/banklink-java-sdk)

# Banklink Java SDK
Java library for Banklinks. Supported is payment requests and authentications.

## Usage
```java
Protocol protocol = new iPizzaProtocol(
    pem_formatted_public_key,
    pem_formatted_private_key,
    new Vendor(
        "uid42",
        "Test Test",
        "EE411010002050618003"
    )
);

protocol.setTestMode(true);
Banklink seb = new Seb(protocol);
PaymentRequest paymentRequest = seb.prepareRequest(new PaymentRequestParams("2", 0.01, "BitWeb test", "123", "http://requestb.in/18d2oau1", "http://requestb.in/18d2oau1"));
```
... and response handling

```java
PaymentResponse response = (PaymentResponse) seb.handleResponse(post_request_params);
```