for i in $(seq 1 10)
do
  curl http://localhost:8999/postings > /dev/null
done
