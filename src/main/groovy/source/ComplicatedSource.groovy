package source

import groovy.transform.Canonical

@Canonical
class ComplicatedSource extends LineSource {
    private String measurement
    private String unitMeasurement
    private String cbm
    private String ldm
}
