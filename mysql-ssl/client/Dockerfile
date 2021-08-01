FROM debian:stable-slim
RUN apt-get update -y && \
    apt-get install default-mysql-client -y
# For container to keep running
CMD ["tail", "-f", "/dev/null"]