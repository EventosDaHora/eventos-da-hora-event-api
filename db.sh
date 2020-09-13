docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name quarkus_test -e POSTGRES_USER=eventos-da-hora -e POSTGRES_PASSWORD=eventos-da-hora -e POSTGRES_DB=ticketsdb -p 5432:5432 postgres:10.5

