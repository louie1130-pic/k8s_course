FROM eclipse-temurin:17-jdk-focal

ADD https://github.com/aws-observability/aws-otel-java-instrumentation/releases/download/v1.17.0/aws-opentelemetry-agent.jar /app/aws-opentelemetry-agent.jar
ENV JAVA_TOOL_OPTIONS "-javaagent:/app/aws-opentelemetry-agent.jar"


WORKDIR /app

ADD target/spring-boot-docker-0.0.1-SNAPSHOT.jar /app/spring-boot-docker-0.0.1-SNAPSHOT.jar

# OpenTelemetry agent configuration
ENV OTEL_TRACES_SAMPLER "always_on"
ENV OTEL_PROPAGATORS "tracecontext,baggage,xray"
#ENV OTEL_RESOURCE_ATTRIBUTES "service.name=${APP_NAME},service.namespace=${APP_NAME}"
ENV OTEL_RESOURCE_ATTRIBUTES "service.name=PetSearch"
ENV OTEL_IMR_EXPORT_INTERVAL "10000"
# OTEL_EXPORTER_OTLP_ENDPOINT 的預設值是 http://localhost:4317，所以註解掉
# ENV OTEL_EXPORTER_OTLP_ENDPOINT "http://localhost:4317"
ENV OTEL_METRICS_EXPORTER "none"

ENTRYPOINT ["java","-jar","spring-boot-docker-0.0.1-SNAPSHOT.jar"]