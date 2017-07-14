\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{ieeelike}[2017/05/26 IEEE-like document]
 
\newcommand{\headlinecolor}{\normalcolor}
\LoadClass{scrreprt}
\KOMAoptions{twoside=false}
%\usepackage{showframe}
\RequirePackage[utf8]{inputenc}
\RequirePackage[italian]{babel}
\RequirePackage[a4paper, total={6in, 9.5in}]{geometry}
\RequirePackage{graphicx}
\RequirePackage{arev, microtype} % font
\RequirePackage{hyperref}
\RequirePackage{calc}
\RequirePackage{enumitem}
\RequirePackage{booktabs}
\RequirePackage{longtable}
\renewcommand{\arraystretch}{1.2}
\RequirePackage{array}
\RequirePackage[xindy]{glossaries}
\renewcommand{\glossarysection}[2][]{}
\glossarystyle{altlist}
\setlist{nolistsep}
\hypersetup{
    bookmarks=false,    % show bookmarks bar?
    pdftitle={Documentazione di progetto del negozio virtuale di CD},    % title
    pdfauthor={Incudini Massimiliano},                     % author
    pdfsubject={},                        % subject of the document
    pdfkeywords={}, % list of keywords
    colorlinks=true,       % false: boxed links; true: colored links
    linkcolor=black,       % color of internal links
    citecolor=black,       % color of links to bibliography
    filecolor=black,        % color of file links
    urlcolor=purple,        % color of external links
    linktoc=page            % only page is linked
}%

\let\cleardoublepage\clearpage

% ==============================================================
% RIDEFINIZIONE TITOLO
\renewcommand{\maketitle}{%
    \begin{flushright}
        \rule{16cm}{5pt}\vskip1cm
        \begin{bfseries}
            \Huge{DOCUMENTAZIONE DI PROGETTO}\\
            \vspace{1.9cm}
            per\\
            \vspace{1.9cm}
            \@title\\
            \vspace{1.9cm}
            \LARGE{Versione 1.0}\\
            \vspace{1.9cm}
            di \@author\\
            \vspace{1.9cm}
        \end{bfseries}
    \end{flushright}
}
% ==============================================================

\newcommand\TitleFont[1]{\noindent\begin{bfseries}\LARGE{#1}\end{bfseries}}
