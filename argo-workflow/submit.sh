argo submit -n argo --watch templates/dag-iris-classification-workflow.yaml
argo logs -n argo @latest
