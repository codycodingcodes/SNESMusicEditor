org $FFFFFFFF


db $53, $54, $41, $52				; Needed to stop Asar from treating this like an xkas patch.
dw SGEnd-SampleGroupPtrs-$01
dw SGEnd-SampleGroupPtrs-$01^$FFFF
SampleGroupPtrs:


dw $0000, SGPointer01, SGPointer02, SGPointer03, SGPointer04, SGPointer05, SGPointer06, SGPointer07, SGPointer08, SGPointer09, SGPointer0A, $0000, $0000, $0000, $0000, $0000
dw $0000, $0000, $0000, $0000, $0000, $0000, $0000, $0000, $0000, $0000, $0000, $0000, $0000, $0000, $0000, $0000
dw $0000, $0000, $0000, $0000, $0000, $0000, $0000, $0000, $0000, $0000, $0000, $0000, $0000, $0000, $0000, $0000
dw $0000, $0000


SGPointer01:

SGPointer02:

SGPointer03:

SGPointer04:

SGPointer05:

SGPointer06:

SGPointer07:

SGPointer08:

SGPointer09:

SGPointer0A:
db $14
dw $0000, $0001, $0002, $0003, $0004, $0005, $0006, $0007, $0008, $0009, $000A, $000B, $000C, $0014, $000E, $0014, $0010, $0014, $0012, $0013
SGEnd: