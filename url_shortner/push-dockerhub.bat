@echo off
set /p DOCKERHUB_USERNAME="Enter your Docker Hub username: "

echo.
echo Step 1: Building image...
docker-compose build

echo.
echo Step 2: Tagging image...
docker tag url_shortner-url-shortener %DOCKERHUB_USERNAME%/url-shortener:latest

echo.
echo Step 3: Pushing to Docker Hub...
docker push %DOCKERHUB_USERNAME%/url-shortener:latest

echo.
echo Image pushed successfully!
echo Docker Hub URL: https://hub.docker.com/r/%DOCKERHUB_USERNAME%/url-shortener
pause