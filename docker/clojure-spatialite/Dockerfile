FROM clojure:lein-2.5.1
MAINTAINER Tobias Quinn <tobias@tobiasquinn.com>

RUN apt-get update
RUN apt-get -y dist-upgrade
RUN apt-get -y install devscripts

RUN useradd -ms /bin/bash gastos
USER gastos
ENV HOME /home/gastos
WORKDIR /home/gastos
RUN git clone git://anonscm.debian.org/pkg-grass/spatialite.git
WORKDIR /home/gastos/spatialite
RUN git checkout debian/4.2.1.rc1-1.exp2
RUN mk-build-deps
USER root
RUN dpkg -i *.deb || true
RUN apt-get -fy install
USER gastos
RUN cd .. && wget http://www.gaia-gis.it/gaia-sins/libspatialite-sources/libspatialite-4.2.1-rc1.tar.gz && \
mv libspatialite-4.2.1-rc1.tar.gz spatialite_4.2.1~rc1.orig.tar.gz
RUN rm spatialite-build-deps_4.2.1~rc1-1~exp2_all.deb
RUN debuild || true
USER root
RUN dpkg -i ../libsqlite3-mod-spatialite_4.2.1~rc1-1~exp2_amd64.deb
