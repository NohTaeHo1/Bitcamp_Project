# Use the official Python image from the Docker Hub
FROM python:3.11-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the requirements.txt file into the container
COPY requirements.txt /app/

# Install the dependencies
RUN pip install --no-cache-dir -r requirements.txt

# Copy the rest of the application code into the container
COPY . /app/

# Set the environment variable for the application
ENV PYTHONUNBUFFERED=1

# Define the command to run the application
# Replace `your_script.py` with the main script of your application
CMD ["python", "your_script.py"]