# Configurando AWS localstack

`aws configure --profile localstack`

`AWS_ACCESS_KEY_ID="test"`

`AWS_SECRET_ACCESS_KEY="test"`

`AWS_DEFAULT_REGION="us-east-1"`

`DEFAULT OUTPUT="json"`

# Criando filas

`aws sqs create-queue --endpoint-url http://localhost:4566 --queue-name pedido-queue --profile localstack`

`aws sqs create-queue --endpoint-url http://localhost:4566 --queue-name pedido-update-status-queue --profile localstack`

`aws sqs create-queue --endpoint-url http://localhost:4566 --queue-name logistica-queue --profile localstack`

`aws sqs create-queue --endpoint-url http://localhost:4566 --queue-name produto-queue --profile localstack`
