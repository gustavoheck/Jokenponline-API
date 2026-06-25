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

set "PUB_BODY=MCowBQYDK2VwAyEARoUOUYqYMvxrlJ1TQG0/i4/DXyyEskLuTzn3FtPEfI0="
set "PRIV_BODY=MC4CAQAwBQYDK2VwBCIEIBOc8rV10eq5f1GocHfk03VVd7sj8KACGebHS8c1CC2Z"

set "JWT_PUBLIC=%START_PUB%\n%PUB_BODY%\n%END_PUB%"
set "JWT_PRIVATE=%START_PRIV%\n%PRIV_BODY%\n%END_PRIV%"

docker exec -i -e VAULT_ADDR=http://127.0.0.1:8200 %CONTAINER_NAME% vault login heckvault
docker exec -i -e VAULT_ADDR=http://127.0.0.1:8200 %CONTAINER_NAME% vault kv put secret/jokenponline security.jwt.public-key="%JWT_PUBLIC%" security.jwt.private-key="%JWT_PRIVATE%"

echo success