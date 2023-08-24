# Use an official Node.js runtime as the base image
FROM node:lts-alpine AS build

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to the container
COPY package*.json ./

# Install npm dependencies
RUN npm install

# Copy the rest of the application code to the container
COPY . .

# Build the Angular app
RUN npm run build --prod

# Use a lightweight Nginx image to serve the Angular app
FROM nginx:alpine

# Copy the built Angular app from the previous stage to the Nginx web root directory
COPY --from=build /app/dist/ /usr/share/nginx/html

# Expose port 80 for the Nginx server
EXPOSE 4200

# Start Nginx when the container runs
CMD ["nginx"]
