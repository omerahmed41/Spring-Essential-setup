cov-verify:
	./mvnw clean verify
cov-report:
	./mvnw jacoco:report
test:
	./mvnw clean test

validate-style:
	./mvnw validate

setup-pre-commit:
	cp pre-commit .git/hooks/pre-commit && chmod +x .git/hooks/pre-commit

