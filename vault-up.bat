@echo off

set CONTAINER_NAME=spring_vault
docker exec -i -e VAULT_ADDR=http://127.0.0.1:8200 %CONTAINER_NAME% vault login heckvault
docker exec -i -e VAULT_ADDR=http://127.0.0.1:8200 %CONTAINER_NAME% vault kv put secret/jokenponline security.jwt.public-key="-----BEGIN PUBLIC KEY-----\nMCowBQYDK2VwAyEARoUOUYqYMvxrlJ1TQG0/i4/DXyyEskLuTzn3FtPEfI0=\n-----END PUBLIC KEY-----" security.jwt.private-key="-----BEGIN PRIVATE KEY-----\nMC4CAQAwBQYDK2VwBCIEIBOc8rV10eq5f1GocHfk03VVd7sj8KACGebHS8c1CC2Z\n-----END PRIVATE KEY-----"
echo success