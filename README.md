![pipeline status](https://gitlab.com/loris.occhipinti2/spacebox-cicd/badges/main/pipeline.svg)
# Spacebox

A basic application demonstrating [IPFS](https://ipfs.io/) for collaborative data analysis, from the perspective of  a Data
Analysis Provider. 

More about the idea behind this repo can be found in this [article](https://levelup.gitconnected.com/a-spacewalk-with-ipfs-and-geeky-farmers-ab6a2ee14906)

## Description

This project exposes a simple API to start a data analysis task over a list of file CIDs stored in IPFS. The application
code then simulates a data analysis task and stores the encoded results in IPFS.

The available APIs are:

- `POST /api/v1/tasks` to start a new analysis task execution
- `GET /api/v1/contents/{hash}` to retrieve an index of contents from an IPFS CID

Tech stack:

- Kotlin
- IPFS
- Gradle v7
- Springboot
- Postgres

## Limitations

This repository shows only a tiny portion of a theoretical solution: it doesn't collect and upload raw data and it doesn't
perform any kind of orchestration or real data analysis, for instance. Moreover, it _encodes_ results rather than 
encrypting them with the public key provided by the owner of data.

