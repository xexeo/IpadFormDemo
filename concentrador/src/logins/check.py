import json, csv

f = open("/home/mangeli/Desenvolvimento/ipaddnit/concentrador/src/logins/users.json", 'r')
json_file = json.load(f)
f2 = open("/home/mangeli/Desenvolvimento/ipaddnit/concentrador/src/logins/POSTOS_SISTEMAS_fase 2-2.csv")

csv_postos=csv.reader(f2, delimiter="\t", quotechar='"')

for row in csv_postos:
    # row[1], row[25]
    for login in json_file['logins']:
        if int(login['usr'])==int(row[1]):
            print row[1], login['trecho'], "\n", row[1], row[25], "\n============="
            login['trecho'] = row[25]
f.close()
#f = open("/home/mangeli/Desenvolvimento/ipaddnit/concentrador/src/logins/users.json", 'w')
#json.dump(json_file,f,indent=4)
#f.close()    
