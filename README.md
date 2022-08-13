# Getting Started



#### docker env
minio
```dockerfile
docker run -p 9000:9000 -p 9090:9090 -d --name minio \
  -e "MINIO_ACCESS_KEY=minio123456" \
  -e "MINIO_SECRET_KEY=minio123456" \
  -v ~/docker/data/minio:/data \
  -v ~/docker/conf/minio:/root/.minio \
  minio/minio server /data --console-address ":9090" -address ":9000"
```