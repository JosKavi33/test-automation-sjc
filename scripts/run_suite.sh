#!/bin/bash

TAG=$1
BROWSER=${2:-chrome}
HEADLESS=${3:-true}

if [ -z "$TAG" ]; then
  echo "Please provide a tag to filter (e.g., @smoke)"
  exit 1
fi

echo "Running tests with tag: $TAG"
echo "Browser: $BROWSER, Headless: $HEADLESS"

rm -rf target/allure-results

./mvnw test \
  -Dtest=principal.RunTests \
  -Dcucumber.filter.tags="$TAG" \
  -Dbrowser="$BROWSER" \
  -Dheadless="$HEADLESS"

echo "Generating Allure Report..."
./mvnw allure:report
