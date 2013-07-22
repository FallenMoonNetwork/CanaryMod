#!/usr/bin/perl -w
use strict;
use Data::Dumper;

if ($#ARGV < 0) {
    printf STDERR "Usage: %s <server.srg>\n", $0;
    exit 1;
}

my %ob_deob = ();

open(SRG, $ARGV[0]);
while (<SRG>) {
    if (m|^FD: \S+/(\S+) \S+/(\S+)\r?$| || m|^MD: \S+/(\S+) \S+ \S+/(\S+) \S+\r?$|) {
        $ob_deob{$2} = $1;
    }
}
close SRG;

#print Dumper(\%ob_deob);

foreach my $file (<src/*.java>) {
    next if $file =~ /O[A-Z].*/;
    my $oldfile = "$file.old";
    if (-e $oldfile || !rename $file, $oldfile) {
        printf STDERR "Error while renaming $file: " . ($! || "file exists") . "\n";
        exit 1;
    }

    open(FILE, $oldfile);
    open(NEWFILE, ">", $file);
    my $nextline = undef;
    while (<FILE>) {
        if ($nextline) {
            print NEWFILE $nextline;
            $nextline = undef;
            next;
        }

        if (m|^(\s+)// SRG (.*)$|) {
            print NEWFILE;
            my $space = $1;
            my $code = $2;
            $code =~ s!f(?:unc|ield)_\d+_[a-zA-Z]+_?!$ob_deob{$&}!g;
            $nextline = $space . $code . "\n";
            next;
        }
        print NEWFILE;
    }
    close NEWFILE;
    close FILE;
}

# vim: set et sts=4 sw=4:
