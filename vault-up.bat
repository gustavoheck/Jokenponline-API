@echo off
:: ========================================================================
:: AMBIENTE DE DESENVOLVIMENTO APENAS (LOCALHOST)
:: Estas chaves sao geradas para fins de teste local e mock do Spring/JWT.
:: Nao sao credenciais de producao e nao expoem dados reais.
:: Essa vault sequer sai do docker no localhost.
:: ========================================================================

set CONTAINER_NAME=spring_vault

set "START_PUB=-----BEGIN PUBLIC KEY-----"
set "END_PUB=-----END PUBLIC KEY-----"
set "START_PRIV=-----BEGIN PRIVATE KEY-----"
set "END_PRIV=-----END PRIVATE KEY-----"

set "PUB_BODY=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtoxC2Cc//sk5lNmBeWR97EnlSJigK5YWV+DSVpPJNcyNbuW1dY6m9AcXAD5vhQJKvNf/H3iOnjW31d4G+7BHkz7o9KBS3IfHmS9++YE+tp17E2RwHU6m7COYyoBhsMNtS+Gl5ErNwVQ+tW/2oN2/2fd+CtlTFTNrS7qIgAEA4Y+LAUtmUEI6IxmxmpfnEgsqk8rsKDME4dolWJNburN0tdjBR1QD0dMcpM+hRtGCwTZb2UzH0LRSV+AxJKR5jk4B85NQo3g2ZGNW+r2fqw7y0p1qUqiLVWU6KL7qFQRAzyv4aWf2uuFw4nZ1AwBhQSV3lMV6AmRQBcHBM0hOcJ9E1wIDAQAB"

set "PRIV_BODY=MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC2jELYJz/+yTmU2YF5ZH3sSeVImKArlhZX4NJWk8k1zI1u5bV1jqb0BxcAPm+FAkq81/8feI6eNbfV3gb7sEeTPuj0oFLch8eZL375gT62nXsTZHAdTqbsI5jKgGGww21L4aXkSs3BVD61b/ag3b/Z934K2VMVM2tLuoiAAQDhj4sBS2ZQQjojGbGal+cSCyqTyuwoMwTh2iVYk1u6s3S12MFHVAPR0xykz6FG0YLBNlvZTMfQtFJX4DEkpHmOTgHzk1CjeDZkY1b6vZ+rDvLSnWpSqItVZToovuoVBEDPK/hpZ/a64XDidnUDAGFBJXeUxXoCZFAFwcEzSE5wn0TXAgMBAAECggEAGHywe3H/FcyPXVC0m1XoGwSp7Yw6XBkUzof5dHn8O9ZrGHesLjn1r6FYgVoJYpm1RdIMRn/zqEmuFdG5NRTnR7C2K25q1GtK/S5PsDoZRqzRoCnRF5VVMzLujCd0M0kbxa8zWMV8sT99mUq3a3Ujhve54CGukUGlqxxkCBZaXxHu+Yz3coUcwe1TMIUPU1uv2KruVUnw3g5dL80BFNz2paw8BISJr4TYJNp3WcPb3xlyGG2OD0zRfuZ90uXCazeUooDcCJ27F4hhcR9KW+gwlmG+9dBGCSaTNzQ5lCLmE/Rlwpz5hGUPlnFeMImfpJ2mMhOyE9+2uRMs0zAOlKOlLQKBgQD1PkYCCtcD2DcQpiQRrtn+2CvqbQqlHiUB5Utw4jpPb1+1h4fI5hEGlpuSHNyPepASb3xulrp1UuYVEjG2BTOaRVy22k/GnqWniqNjTsiZbsEH63uQQ+CPncLdJFre7R7G3NljUUpoBDk2XebKlRihpBSVTHFLyhFlUZKkntCZEwKBgQC+jgJyHQNLK6bJziW6Zny1Fk2Ij4XdVeELaWTYx8k8QvyZrGW08AEDz5FNA2BSbV/9bFQQa67MLn9xujKBgGB3rzfVGDEoKcY33tqqxMBoCk/EkLawfTYDAHO8XSUANWidZSpEiN9YZmRJPhfIX4iHa73GSbJGWM1jxviX0U5BrQKBgQCEWlRb+8+osoKdSJzEslHR+IZS4QBStl8Qk9fQFzeS8S98/6TnZkqf/J3/D/Z0tJeWa3aDFHOSp+emTYhojIwZ4P81a8KMFcifD/I3wSf/rkcc6Bhqdkd2GpU01c746o2mkz/7Gr8GPelGjwzkHe0KJ0w4euXWGjqjLI/wqgk34wKBgCHLf8WLPbVMNZVe2TQYzw8yuGd6xMv/Wh0zAO6taBQm8TOz7onLcvKhEmztJ10XBsokNzh+UI7nxDSASErNYM55JdbGJpmv5varsG5O3KzW/1SmMbBVVIntv5jtkA1ca4RWl8t11t6PskOHHqKrxsP1qL+M/HAbebmzZQ1P0qg1AoGBAO72HaXAQREpTlZBx9sS9+KpQaYNwkjvXOcVrQCNGGfNYteUEGBTMoWHaxQ+MoKgeE3+nOLGd33qvQxZsN+mJ/5efZxta9lr1TYGn+Q/xSztsaL946FHVzw6XIsNazutMFkSF7zo5a6q6/MrnRRtCljSzngNQWXnli8tGOkwwrT7"

set "JWT_PUBLIC=%START_PUB%\n%PUB_BODY%\n%END_PUB%"
set "JWT_PRIVATE=%START_PRIV%\n%PRIV_BODY%\n%END_PRIV%"

docker exec -i -e VAULT_ADDR=http://127.0.0.1:8200 %CONTAINER_NAME% vault login heckvault
docker exec -i -e VAULT_ADDR=http://127.0.0.1:8200 %CONTAINER_NAME% vault kv put secret/jokenponline security.jwt.public-key="%JWT_PUBLIC%" security.jwt.private-key="%JWT_PRIVATE%"

echo success