FROM ubuntu:latest

RUN apt-get update && \
    apt-get -y install squid3

COPY squid.conf /etc/squid/squid.conf
COPY squid.htpasswd /etc/squid/squid.htpasswd

CMD service squid start && service --status-all && /bin/bash
