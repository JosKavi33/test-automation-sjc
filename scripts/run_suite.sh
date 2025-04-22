#!/bin/bash
# Run the tests with a specific group and browser configuration

# Check if the group parameter was passed
if [ $# -lt 1 ]; then
  echo "Please specify a test group (example: @regression, @smoke)"
  exit 1
fi

# Input parameters
TAG=$1
BROWSER=${2:-chrome}  # If not provided, it will default to Chrome
HEADLESS=${3:-true}    # If not provided, it will default to headless mode

# Check if the browser is valid
if [[ "$BROWSER" != "chrome" && "$BROWSER" != "firefox" && "$BROWSER" != "edge" ]]; then
  echo "Error: Only 'chrome', 'firefox', and 'edge' are supported browsers."
  exit 1
fi

# If headless is true, add the corresponding option
if [[ "$HEADLESS" == "true" ]]; then
  HEADLESS_OPTION=true
else
  HEADLESS_OPTION=false
fi

# Run Maven with the appropriate options
echo "Running tests with the group: $TAG"
echo "Browser: $BROWSER in headless mode: $HEADLESS_OPTION"
./mvnw test -Dtest=principal.RunTests -Dcucumber.options="--tags $TAG" -Dbrowser=$BROWSER -Dheadless=$HEADLESS_OPTION
