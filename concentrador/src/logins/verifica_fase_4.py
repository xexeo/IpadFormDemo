# -*- coding: utf-8 -*-
"""
Created on Sun Sep 10 18:14:51 2017

@author: mangeli
usar python 3
"""

import json, csv

with open("users_fase2_old.json", 'r') as f:
    logins = json.load(f)
    
f = open("POSTOS_FASE_4.csv")
postosFase4 = csv.reader(f, delimiter='\t')

#testa se os postos tem senha
#for row in postosFase4:
#    achei = False
#    for login in logins['logins']:
#        if int(login['usr']) == int(row[0]):
#            achei = True
#    if not achei:
#        print 'posto', row[0], 'nao tem senha'

''' todos os postos da fase 4 já estão no sistema '''



#modifica a descrição para a constante na planilha
for row in postosFase4:
    for login in logins['logins']:
        if int(login['usr']) == int(row[0]):
            print (row[0], row[14], "\n", login['usr'], login['trecho'])
            print ('\n============')
            login['trecho'] = row[14]

f.close() #fecha planilha

with open("users.json", 'w') as f:
    json.dump(logins,f,indent=4, ensure_ascii=False)

