@echo off
set /p DOCKERHUB_USERNAME="Enter your Docker Hub username: "
set /p VERSION="Enter version tag (e.g., v1.0): "

echo.
echo Building and tagging image...
docker-compose build

echo.
echo Creating tags...
docker tag url_shortner-url-shortener %DOCKERHUB_USERNAME%/url-shortener:latest
docker tag url_shortner-url-shortener %DOCKERHUB_USERNAME%/url-shortener:%VERSION%

echo.
echo Pushing to Docker Hub...
docker push %DOCKERHUB_USERNAME%/url-shortener --all-tags

echo.
echo All tags pushed successfully!
echo Available tags: latest, %VERSION%
pause