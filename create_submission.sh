#!/bin/bash
DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
rm submission.zip

pushd . 
cd $DIR/src/main/scala
zip -r submission.zip .
mv submission.zip $DIR
popd
