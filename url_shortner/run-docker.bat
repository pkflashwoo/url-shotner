@echo off
echo Building and running URL Shortener Docker container...

echo.
echo Step 1: Building Docker image...
docker-compose build

if %ERRORLEVEL% NEQ 0 (
    echo Build failed!
    pause
    exit /b 1
)

echo.
echo Step 2: Starting container...
docker-compose up -d

if %ERRORLEVEL% NEQ 0 (
    echo Failed to start container!
    pause
    exit /b 1
)

echo.
echo Container started successfully!
echo Application will be available at: http://localhost:8080
echo.
echo To view logs: docker-compose logs -f
echo To stop: docker-compose down
echo.
pause