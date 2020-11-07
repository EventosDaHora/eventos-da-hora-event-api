docker run --ulimit memlock=-1:-1 -it -d --rm=true --memory-swappiness=0 --name ticketsdb -e POSTGRES_USER=eventos-da-hora -e POSTGRES_PASSWORD=eventos-da-hora -e POSTGRES_DB=ticketsdb -p 5437:5432 postgres:10.5

