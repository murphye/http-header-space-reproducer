# http-header-space-reproducer


docker build -t murphye/http-header-space-reproducer .
docker run -t -i -p 8080:8080 murphye/http-header-space-reproducer

docker tag murphye/http-header-space-reproducer gcr.io/mattel-305318/http-header-space-reproducer
docker push gcr.io/mattel-305318/http-header-space-reproducer
