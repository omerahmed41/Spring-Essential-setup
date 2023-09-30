cov-verify:
	./mvnw clean verify
cov-report:
	./mvnw jacoco:report
test:
	./mvnw clean test

validate-style:
	./mvnw validate

