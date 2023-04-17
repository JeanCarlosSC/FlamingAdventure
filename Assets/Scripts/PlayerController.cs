using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerController : MonoBehaviour {
    public float horizontalInput;
    public float speed = 10.0f;
    public float xRange = 15;
    public GameObject projectilePrefab;

    // Start is called before the first frame update
    void Start() {
        
    }

    // Update is called once per frame
    void Update() {
        horizontalInput = Input.GetAxis("Horizontal");
        transform.Translate(Vector3.right * horizontalInput * Time.deltaTime * speed);

        // keep the player in bounds
        if(transform.position.x < -xRange) {
            transform.position = new Vector3(-15, transform.position.y, transform.position.z);
        }
        else if(transform.position.x > xRange) {
            transform.position = new Vector3(15, transform.position.y, transform.position.z);
        }
    }
}
