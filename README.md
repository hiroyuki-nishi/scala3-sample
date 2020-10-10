このプロジェクトは　Scala3.0 Dotty + SAMで構成されています

# Requiments
## sbt project compiled with Dotty

### Usage

This is a normal sbt project, you can compile code with `sbt compile` and run it
with `sbt run`, `sbt console` will start a Dotty REPL.

For more information on the sbt-dotty plugin, see the
[dotty-example-project](https://github.com/lampepfl/dotty-example-project/blob/master/README.md).

### Deploy
SAM概要
https://docs.aws.amazon.com/ja_jp/serverless-application-model/latest/developerguide/serverless-getting-started-hello-world.html

template.yamlについて
https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/automating-updates-to-serverless-apps.html

sam packageコマンドについて
https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/sam-cli-command-reference-sam-package.html

1. Lambdaデプロイ先S3バケット作成
```
aws s3 mb s3://sam-sample --aws-profile=<AWSプロファイル>
aws s3 mb s3://sam-sample
```

2. sam package で、上で作成したS3バケットへ実行ファイルをアップロード & デプロイ用テンプレートファイルを生成します
```
sam package --s3-bucket sam-sample --output-template-file output.yml
```

3.sam deploy で、Lambdaをデプロイします。
```
sam deploy --template-file output.yml --stack-name scala3-sample --capabilities CAPABILITY_IAM
```

--------------------
#### デプロイしたLambdaの削除方法
・Webコンソールから該当のsamのCloudformationを削除する
or
・
```
aws cloudformation delete-stack --stack-name scala3-sample
```

--------------------


### TODO

[x]: samで作成したLambdaの削除方法
[x]: samで作成したLambdaの関数名変更
[ ]: DynamoDBのコード作成（CDK)
[ ]: Layerにコードを分ける
[ ]: Layer参照デプロイ?
[ ]: 時間計測
[ ]: samのLambdaのバージョニング残ってるか調べる
[ ]: SAMを使ってローカルでLambdaを実行する方法
