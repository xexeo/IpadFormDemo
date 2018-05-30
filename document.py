# -*- coding: utf-8 -*-
"""
Created on Wed May 30 16:21:28 2018

@author: Geraldo
"""
import os

# começa definindo as constantes

rootdir = "."

NUMERA = True

HEADER = """
\\documentclass{article}
\\usepackage[utf8]{inputenc}
\\usepackage[T1]{fontenc}
\\usepackage{lmodern}


\\begin{document}
"""

FOOTER = """
\\end{document}
"""
FILE_HEADER = """
\\subsection{%s}

\\begin{verbatim}

"""
FILE_FOOTER = """
\\end{verbatim}
"""


# define os arquivos que vão ser usados

arquivos = [ "java", "js", "json" , "css", "html", "py","xml","properties",
            "txt"]

nome = { "java" : "Java",
        "js" : "JavaScript",
        "json" : "JSON",
        "css" : "Cascade Style Sheets (CSS)",
        "html" : "HTML",
        "py" : "Python",
        "xml" : "XML",
        "properties" : "properties",
        "txt": "txt"
        }

# coisas que indicam em um nome de diretorio que não estamos interessados
EXCLUDE = ["node_modules","doc","lib","doxy","resources","images","simple",
           ".settings",".external","concetrador\logs","concentrador\store",
           "ignore","plugins"]


# Como queremos ordenar por tipo de arquivo

def doublein(string,lista):
    for string1 in lista:
        if string1 in string:
            return True
    return False

# problemas no Latex do pandoc
def trata(nome):
    x=nome.replace("_","\\textunderscore ")
    return x.replace("\\","\\backslash ")


#abre o arquivo de saída que receberá todo o código
with open('output.latex','w',encoding="utf8") as fout:
    fout.writelines(HEADER)
    # vai ordenar os arquivos por extensão
    for extensao in arquivos:
        print("EXTENSAO: ",extensao)
        
        # pega todos os arquivos a priori
        for root, _, files in os.walk(rootdir):
                
            for name in files:
 
                
                completo = os.path.join(root,name)
                _,extatual = os.path.splitext(completo)
              
                # não estamos interessados em coisas em alguns diretorios
                if doublein(root,EXCLUDE):
                    continue
                
                # se estou na extensão que está sendo tratada agora
                if extatual == "."+extensao :
                    
                    fout.writelines(FILE_HEADER % trata(completo))
                    print(trata(completo))
                    
                    # tivemos muitos problemas de arquivo
                    try:
                        linha = 0
                        with open(completo, 'r',encoding="utf8") as fin:
                            for lines in fin:
                                
                                # defende de UTF-8 errados
                                # aparecem em vários lugares devido ao uso
                                # de vários sistemas
                                line=bytes(lines,'utf-8').decode('utf-8','ignore')
                                
                                linha+=1
                                if NUMERA:
                                    line="%4d:" % linha + line
                                fout.writelines(line)
                    except:
                        print("arquivo de entrada: ",completo)
                        raise

                    fout.writelines(FILE_FOOTER)

    fout.writelines(FOOTER)
    

            

