#!/bin/sh

FILE_TO_MIGRATE="baggers-rh.js"
INPUT_DIR="bblfr_input"
OUTPUT_DIR="bblfr_output"
BLAME_FILE="bblfr_blame"

rm -rf $INPUT_DIR
rm -rf $OUTPUT_DIR
mkdir $OUTPUT_DIR
touch $OUTPUT_DIR/$FILE_TO_MIGRATE

git clone https://github.com/brownbaglunch/bblfr_data.git $INPUT_DIR
cd $INPUT_DIR
git checkout gh-pages
git blame -c $FILE_TO_MIGRATE | grep '"name":' | cut -f 3,4 | sed 's/ .*"name": "/ /' | sed 's/".*//' > $BLAME_FILE
cd - 1>/dev/null

./gradlew run -Dexec.args="$INPUT_DIR/$FILE_TO_MIGRATE $INPUT_DIR/$BLAME_FILE $OUTPUT_DIR/$FILE_TO_MIGRATE"

echo "Done"
