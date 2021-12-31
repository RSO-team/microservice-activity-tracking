docker build -t lgaljo/rt_basketball_activity_tracking  -f Dockerfile_with_maven_build .
docker tag lgaljo/rt_basketball_activity_tracking  lgaljo/rt_basketball_activity_tracking :latest
docker push -a lgaljo/rt_basketball_activity_tracking