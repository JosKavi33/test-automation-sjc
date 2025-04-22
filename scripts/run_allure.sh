#!/bin/bash
echo "Generating Allure Report..."
./mvnw allure:report
echo "Opening Allure Report..."
./mvnw allure:serve
