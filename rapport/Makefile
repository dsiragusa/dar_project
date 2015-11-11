LATEX = pdflatex
OPTIONS = -file-line-error

all: texToPdf

texToPdf: rapport.tex manuelUtilisateur.tex developpement.tex fonctionnalites.tex complements.tex
	$(LATEX) $(OPTIONS) rapport.tex

clean:
	rm -f *.aux *.log *.out *.toc
