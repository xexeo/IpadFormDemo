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

EXCLUDE = ["node_modules","doc","lib","doxy","resources","images","simple",
           ".settings",".external","concetrador\logs","concentrador\store",
           "ignore","plugins"]


# Como queremos ordenar por tipo de arquivo

def doublein(string,lista):
    for str in lista:
        if str in string:
            return True
    return False

# problemas no Latex do pandoc
def trata(nome):
    x=nome.replace("_","\\textunderscore ")
    return x.replace("\\","\\backslash ")



with open('output.latex','w',encoding="utf8") as fout:
    fout.writelines(HEADER)
    for extensao in arquivos:
        print("EXTENSAO: ",extensao)
        for root, subFolders, files in os.walk(rootdir):
            
            for name in files:
 
                completo = os.path.join(root,name)
                _,extatual = os.path.splitext(completo)
              
                if doublein(root,EXCLUDE):
                    continue
                
                if extatual == "."+extensao :
                    fout.writelines(FILE_HEADER % trata(completo))
                    print(trata(completo))
                    try:
                        linha = 0
                        with open(completo, 'r',encoding="utf8") as fin:
                            for lines in fin:
                                # defende de UTF-8 errados
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
    

            

