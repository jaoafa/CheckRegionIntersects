# CheckRegionIntersects

![Java CI with Maven](https://github.com/jaoafa/MyMaid3/workflows/Java%20CI%20with%20Maven/badge.svg)
![Support jdk](https://img.shields.io/badge/Support%20jdk-oraclejdk8-red.svg)
![Author](https://img.shields.io/badge/Author%20MinecraftID-mine__book000-orange.svg)
[![License](https://img.shields.io/github/license/jaoafa/CheckRegionIntersects)](https://github.com/jaoafa/CheckRegionIntersects/blob/master/LICENSE)

[jao Minecraft Server](https://jaoafa.com)における、自治体申請時に既存自治体と交差していないかを調べるためのJavaアプリケーションです。

## 使い方

1. [Releases](https://github.com/jaoafa/CheckRegionIntersects/releases)から最新の`CheckRegionIntersects.jar`をダウンロード。
2. 比較する自治体の範囲情報を後述する[#範囲情報JSON構築](#範囲情報JSON構築)を使ってそれぞれ構築。
3. ダウンロード先フォルダをカレントディレクトリとしてターミナル(`cmd.exe`, `powershell.exe`...)を開き、`java -jar CheckRegionIntersects.jar <比較する範囲情報JSON1> <比較する範囲情報JSON2>`と実行。
4. 終了ステータスが0なら交差無し、1なら交差あり。

例: `爆新地`と`とまちー市`が交差しているか調べるならば(2020/07/19現在):

```shell
java -jar CheckRegionIntersects.jar [{"x":-513,"z":-512},{"x":-513,"z":512},{"x":512,"z":512},{"x":512,"z":-512}] [{"x":528,"z":-513},{"x":528,"z":512},{"x":1128,"z":512},{"x":1128,"z":-513}]
```

※場合によっては各JSONをアポストロフィー(`'`)か何かで囲む必要があるかも。

## 範囲情報JSON構築

爆新地を例とした場合、以下は範囲情報。

```text
#1 -513 -512
#2 -513 512
#3 512 512
#4 512 -512
```

これを以下のようなJSONに変換する。

```json
[
    {
        "x": -513,
        "z": -512
    },
    {
        "x": -513,
        "z": 512
    },
    {
        "x": 512,
        "z": 512
    },
    {
        "x": 512,
        "z": -512
    }
]
```

これを1列のJSONにして引数として使う。

## ライセンス

ライセンスは[MIT License](https://github.com/jaoafa/CheckRegionIntersects/blob/master/LICENSE)を適用します。
