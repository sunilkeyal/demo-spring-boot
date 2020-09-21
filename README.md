This demo2 application is a simple spring boot application with no other dependencies
So that it can be deployed in Azure, Kubernetes etc.
 

# Kubernetes install in ubuntu and general commands
https://kubernetes.io/docs/reference/kubectl/cheatsheet/

- sudo snap install microk8s --classic
- microk8s status --wait-ready
- sudo ufw allow in on cni0 && sudo ufw allow out on cni0
- sudo ufw default allow routed
- sudo usermod -a -G microk8s $USER
- microk8s enable dashboard dns registry istio
- microk8s kubectl get all --all-namespaces
- microk8s dashboard-proxy
- microk8s start
- microk8s stop
- microk8s kubectl get nodes
- microk8s kubectl get services
- microk8s kubectl get pods
- microk8s kubectl cluster-info
- microk8s kubectl get namespaces
- kubectl config set-context --current --namespace=<insert-namespace-name-here>emove

# Create bash alias for kubectl inside .bash_alias file
alias kubectl='microk8s kubectl'

#  First service (microbot) in kubernetes
- microk8s kubectl create deployment microbot --image=dontrebootme/microbot:v1
- microk8s kubectl scale deployment microbot --replicas=4
- microk8s kubectl expose deployment microbot --type=NodePort --port=80 --name=microbot-service
- microk8s kubectl get all --all-namespaces (see the service)
- access the service using http://<ip_address> (get ip address from above command)
- microk8s kubectl delete deployment microbot

# Kubernetes dashboard
https://10.152.183.169/#/overview?namespace=default

# demo2 service in kubernetes
- ./gradlew clean build 
- docker login
- docker build -t sunilkeyal/demo2 .
- docker push sunilkeyal/demo2
- microk8s kubectl create deployment demo2 --image=sunilkeyal/demo2
- microk8s kubectl scale deployment demo2 --replicas=4
- microk8s kubectl expose deployment demo2 --type=NodePort --port=8080 --name=demo2-service
- microk8s kubectl get all --all-namespaces (see the service ip)
http://<service_id>:8080/hello
- microk8s kubectl delete deployment demo2

# How to get token for kubernetes
token=$(microk8s kubectl -n kube-system get secret | grep default-token | cut -d " " -f1)
microk8s kubectl -n kube-system describe secret $token


eyJhbGciOiJSUzI1NiIsImtpZCI6IndScnRTaTdRZElsN1FOTzVwTG1YV3J6djFHUFotbkpOX0t4NzFoQ3prZmcifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJkZWZhdWx0LXRva2VuLThmZ2d0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQubmFtZSI6ImRlZmF1bHQiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiJmMDE2MjNiZi0xMGZiLTQ4MzgtYmZmYy02OTdjYjg4Y2JkYTUiLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6a3ViZS1zeXN0ZW06ZGVmYXVsdCJ9.KfpXQi2hYiXGW0nuJDCoMdqzxz4WkIzbWaB_denq_lbPZC6fCDsgyi4iLsq1TjbYUaVo3uP5zQg0Nlwpk-EGlRgze1QMv22hDlKCmTFUoe9I6IDOMvlk-7-A5SqsMOT1zQNhBsP_FHo4Q4fyf4r1_H-3BG_uBXwQQlFqE2Joh8YxA_BFT7URIIMjh3lJDs1zceR2cch2jg0hXJsSfeMQ5aVNl4QH_kOJgWAOxaXF9sftySF2Z9RPWUj8vfB0sSEJkWtG_N2fjDfJfoRRoszVyAZt5LOPNrAdm04thIZ7Y2Gy92rA0-dj1h0viGknp5gN2HdfH1QgFzfo9VEon0W6zw

# FIX Empty pod/service issue
- kubectl delete clusterrolebinding kubernetes-dashboard
- kubectl create clusterrolebinding kubernetes-dashboard --clusterrole=cluster-admin --serviceaccount=kube-system:kubernetes-dashboard --user=clusterUser
