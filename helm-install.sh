# !/bin/bash

helm list
helm package ./helm -d ../helm-charts/
helm upgrade -f ./helm/local-values.yaml --wait --debug -i clash-bot-events-consumer ./helm