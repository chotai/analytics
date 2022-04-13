FROM --platform=linux/x86-64 ubuntu/squid:4.13-21.10_edge

#ENV DEBIAN_FRONTEND noninteractive
#ENV TZ Etc/UTC
#RUN apt-get update
#RUN apt-get update -qq \
#    && apt-get install -qqy squid3

#RUN service squid start
#RUN service --status-all
#&& tail -f /dev/null

COPY squid.conf /etc/squid/squid.conf
COPY squid.htpasswd /etc/squid/squid.htpasswd
#COPY platops-entrypoint.sh /

#ENTRYPOINT ["/platops-entrypoint.sh"]
